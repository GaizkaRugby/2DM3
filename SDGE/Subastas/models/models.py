# -*- coding: utf-8 -*-

from email.policy import default
from odoo import models, fields, api
from odoo.exceptions import ValidationError 

from datetime import datetime


class producto(models.Model):
    _name = "producto"
    _description = "Producto a subastar"

    _rec_name = "nombre"

    codigo = fields.Integer(string='Código', required=True, default=000)
    nombre = fields.Char(size=15, string='Nombre', required=True)
    desc = fields.Char(size=50, string='Descripción')
    foto = fields.Binary('foto')

    proLot = fields.Many2one(comodel_name='lote', string='Pertenece a')

    @api.constrains('codigo')
    def validarCodigo(self):
        if len(str(self.codigo))!=3:
            raise ValidationError("El codigo ha de ser de tres cifras")

class lote(models.Model):
    _name = "lote"
    _descripcion = "Lote de productos"

    _rec_name = "catnum"

    catnum = fields.Integer(string='Número de catálogo', required=True)
    salida = fields.Float(string='Precio de salida', required=True)
    pujamax = fields.Float(string='Mayor puja', default=0)
    tiempo = fields.Datetime(string='Tiempo de subasta restante')

    lotPro = fields.One2many(comodel_name='producto', inverse_name='proLot', string='Contiene')
    lotPuj = fields.One2many(comodel_name='puja', inverse_name='pujLot', string='Lote-Puja')
    
    @api.constrains('salida')
    def validaPrecioSalida(self):
        if self.salida < 0:
            raise ValidationError("El precio de salida no puede ser negativo")

    @api.onchange('lotPuj')
    def modificarPujaMax(self):
        pujamax = self.lotPuj.cantidad
        #self.env.cr.execute("SELECT cantidad FROM puja WHERE id = " + str(self.lotPuj))
        #pujamax = cantidad


class puja(models.Model):
    _name = "puja"
    _descripcion = "Puja"

    _rec_name = "cantidad"

    dia = fields.Date(string='Dia', default=datetime.now().date(), readonly=True)
    hora = fields.Datetime(string='Hora', default=datetime.now(), readonly=True)
    cantidad = fields.Integer(string='Cantidad')

    pujLot = fields.Many2one(comodel_name='lote', string='Puja-Lote')
    pujCli = fields.Many2one(comodel_name='cliente', string='Puja-Cliente')

    @api.constrains('cantidad')
    def validar_pujamax(self):
        if self.cantidad != "":
                self.env.cr.execute("SELECT pujamax FROM lote WHERE catnum = " + str(self.pujLot.catnum))
                pujaMax = self.env.cr.fetchone()[0]
                if self.cantidad < pujaMax :
                    raise ValidationError("El valor tiene que ser a la puja máxima actual")


class cliente(models.Model):
    _name = "cliente"
    _descripcion = "Cliente"

    _rec_name = "nombre"

    usuario = fields.Char(size=20, string='Usuario', required=True)
    clave = fields.Char(size=30, string='Contraseña', required=True)
    email = fields.Char(size=40, string='Email')
    nombre = fields.Char(size=15, string='Nombre')

    cliPuj = fields.One2many(comodel_name='puja', inverse_name='pujCli', string='Cliente-Puja')

    @api.constrains('usuario')
    def validarUsuario(self):
        if len(self.search([('usuario','=',self.usuario)]))>1:
            raise ValidationError("No puede haber dos usuarios iguales")