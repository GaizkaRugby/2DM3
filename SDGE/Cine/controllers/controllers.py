# -*- coding: utf-8 -*-
# from odoo import http


# class /root/plantilla(http.Controller):
#     @http.route('//root/plantilla//root/plantilla/', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('//root/plantilla//root/plantilla/objects/', auth='public')
#     def list(self, **kw):
#         return http.request.render('/root/plantilla.listing', {
#             'root': '//root/plantilla//root/plantilla',
#             'objects': http.request.env['/root/plantilla./root/plantilla'].search([]),
#         })

#     @http.route('//root/plantilla//root/plantilla/objects/<model("/root/plantilla./root/plantilla"):obj>/', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('/root/plantilla.object', {
#             'object': obj
#         })
