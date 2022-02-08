# -*- coding: utf-8 -*-
# from xml.dom import ValidationErr
from odoo import models, fields, api#, exceptions
from odoo.exceptions import ValidationError 
import re

from datetime import datetime

class director(models.Model):
    _name = "director"
    _descripcion = "Director de la pelicula"

    _rec_name = "nombre"
    
    nombre = fields.Char(size=20, string='Nombre del director', required=True)
    fnac = fields.Date(string='Fecha de nacimiento', required=True)
    nacion = fields.Char(size=15, string='Nacionalidad')
    
    dirige = fields.One2many(comodel_name='pelicula', inverse_name='esDirige', string='Dirige')

    @api.constrains('nombre')
    def validarNombre(self):
        sql = """SELECT nombre FROM director"""
        self.env.cr.execute(sql)
        cont=0
        for rec in self.env.cr.fetchall():
            if self.nombre==rec[0]:
                cont+=1
                if cont > 1:
                    raise ValidationError("No puede haber dos directores con el mismo nombre")

    @api.constrains('fnac')
    def validarAnioNacimiento(self):
        if self.fnac > datetime.now().date():
            raise ValidationError("La fecha de nacimiento no puede ser mayor a la actual")


class pelicula(models.Model):
    _name = "pelicula"
    _descripcion = "Pelicula descripcion"

    _rec_name = "titulo"

    titulo = fields.Char(size=40, string='Titulo', required=True)
    anio = fields.Date(string='Año', required=True)
    nacion = fields.Char(size=15, string='Nacion de la pelicula')
    idioma = fields.Char(size=15, string='Idioma')
    color = fields.Selection([
        ('BL', "blanco y negro"), 
        ('COL', 'color')
    ], string='Color')
    resumen = fields.Text(string='Sinopsis')
    observaciones = fields.Text(string='Observaciones')

    esDirige = fields.Many2one(comodel_name='director', string='Es dirigida')
    peliRep = fields.One2many(comodel_name='reparto', inverse_name='repPeli', string='Pelicula-Reparto')
    
    @api.constrains('anio')
    def validarAnio(self):
        if self.anio < datetime.strptime("01/01/1895","%d/%m/%Y").date():
            raise ValidationError("La pelicula ha de ser posterior a 1895")


class reparto(models.Model):
    _name = "reparto"
    _descripcion = "Reparto de la pelicula"

    _rec_name = "personaje"

    personaje = fields.Char(string='Personaje', required=True)

    repPeli = fields.Many2one(comodel_name='pelicula', string='Reparto-Pelicula')
    repActor = fields.Many2one(comodel_name='actor', string='Reparto-Actor')

    @api.constrains('personaje')
    def validarPersonaje(self):
        if len(self.search([('personaje','=',self.personaje)]))>1:
            if len(self.search([('repActor','=',self.repActor.nombre)]))>1:
                if len(self.search([('repPeli','=',self.repPeli.titulo)]))>1:
                    raise ValidationError("No")


class actor(models.Model):
    _name = "actor"
    _descripcion = "Actor de la pelicula"   

    _rec_name = "nombre"

    nombre = fields.Char(string='Nombre')
    nacion = fields.Char(string='Nacionalidad del actor')

    actorRep = fields.One2many(comodel_name='reparto', inverse_name='repActor', string='Actor-Reparto')
    