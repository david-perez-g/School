coverage = 100

lenght = float(input("Inserte el largo de la habitacion: "))

width1 = float(input("Inserte el ancho del primer lado: "))

width2 = float(input("Inserte el ancho del segundo lado no paralelo al primero: "))

height = float(input("Inserte la altura de la habitacion: "))

coats = float(input("Inserte la cantidad de capas a aplicar: "))

surface_area_lado1 = 2 * (width1 * height)
surface_area_lado2 = 2 * (width2 * height)
surface_area_techo = width1 * width2

surface_area = surface_area_lado1 + surface_area_lado2 + surface_area_techo

coverage_needed = surface_area * coats

cans_of_paint_required = coverage_needed / coverage

print(f"Necesitas {cans_of_paint_required.__round__()} botes de pintura.")
