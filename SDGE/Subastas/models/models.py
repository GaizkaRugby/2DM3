# -*- coding: utf-8 -*-
# from xml.dom import ValidationErr
import string
from odoo import models, fields, api#, exceptions
from odoo.exceptions import ValidationError 
import re

from datetime import datetime

class cliente(models.Model):
    _name = "cliente"
    _descripcion = "cliente del lote"

    _rec_name = "usuario"
    
    usuario = fields.Char(size=20, string='usuario del cliente', required=True)
    nombre = fields.Char(size=20, string='nombre del cliente', required=True)
    clave = fields.Char(size=20, string='clave del cliente')
    email = fields.Char(string='email')
    puja = fields.Float(string='puja')
    
    dirige = fields.One2many(comodel_name='puja', inverse_name='espujacli', string='Dirige')

    @api.constrains('nombre')
    def validarNombre(self):
        sql = """SELECT nombre FROM cliente"""
        self.env.cr.execute(sql)
        cont=0
        for rec in self.env.cr.fetchall():
            if self.nombre==rec[0]:
                cont+=1
                if cont > 1:
                    raise ValidationError("No puede haber dos clientees con el mismo nombre")

    @api.constrains('fnac')
    def validarAnioNacimiento(self):
        if self.fnac > datetime.now().date():
            raise ValidationError("La fecha de nacimiento no puede ser mayor a la actual")


class lote(models.Model):
    _name = "lote"
    _descripcion = "lote descripcion"

    _rec_name = "catnum"

    catnum = fields.Char(size=40, string='Titulo', required=True)
    salida = fields.Date(string='Año', required=True)
    pujamax = fields.Char(size=15, string='Nacion de la lote')
    tiempo = fields.Char(size=15, string='Idioma')
    
    pujalote = fields.One2many(comodel_name='puja', inverse_name='espujalote', string='lote-puja')
    pujapro = fields.One2many(comodel_name='reparto', inverse_name='espujapro', string='lote-producto')
    
    @api.constrains('anio')
    def validarAnio(self):
        if self.anio < datetime.strptime("01/01/1895","%d/%m/%Y").date():
            raise ValidationError("La lote ha de ser posterior a 1895")


class puja(models.Model):
    _name = "puja"
    _descripcion = "Puja"


    cantidad = fields.Char(string='Personaje', required=True)
    dia = fields.Date('dia')
    hora = fields.Datetime('hora')

    espujacli = fields.Many2one(comodel_name='cliente', string='puja-cliente')
    espujalote = fields.Many2one(comodel_name='lote', string='puja-lote')

    @api.constrains('personaje')
    def validarPersonaje(self):
        if len(self.search([('personaje','=',self.personaje)]))>1:
            if len(self.search([('repActor','=',self.repActor.nombre)]))>1:
                if len(self.search([('repPeli','=',self.repPeli.titulo)]))>1:
                    raise ValidationError("No")


class producto(models.Model):
    _name = "producto"
    _descripcion = "producto del lote"   

    _rec_name = "codigo"

    codigo = fields.Char(string='Nombre')
    nombre = fields.Char(string='Nacionalidad del actor')
    desc = fields.Char(string='Nombre')
    foto = fields.Binary(string='foto', help="Seleccionar imagen aquí")

    espujapro = fields.Many2one(comodel_name='puja', string='puja-producto')
    