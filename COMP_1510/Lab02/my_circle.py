pi = 3.14159
radius = float(input("Digite el radio del circulo: "))

circumference = 2 * pi * radius

print(f"La longitud de la circunferencia es de {circumference}")

area = pi * radius**2

print(f"El area de la circunferencia es de {area}")

doble_radius = radius * 2

circumference_2 = 2 * pi * doble_radius
area_2 = pi * doble_radius**2

circumference_razon = circumference_2 / circumference
area_razon = area_2 / area

print(f"\nSi doblaramos el valor del radio, la circunferencia seria {circumference_razon} veces mayor que la original, y el area seria {area_razon} veces mayor que la original.")