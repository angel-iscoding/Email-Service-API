# Email Automation System with Spring Boot

Api de correo como servicio desarrollada con Spring Boot.
La idea es que cada cliente pueda subir sus propias plantillas de correos personalizables con placeholders (variables) y enviar a sus usuarios de manera automatica y simple de implementar.

Funciones:
- Autentificacion MFA
- Nortificaciones
- Correos programados
- Correos a multiples usuarios de manera automatizada
- Reutilizacion de plantillas
- Entorno facil de implementar

## Como implementar

POST /api/clientes/registrar
    Body:
        {
            "name": "Mi App",
            "smtpHost": "smtp.gmail.com",
            "smtpUser": "miapp@gmail.com",
            "smtpPassword": "contraseña-smtp"
        }

1. Cada cliente tendrá que registrarse en la API ingresando sus credenciales SMTP de la cuenta que quiera enviar los correos.
2. Una vez completado el registro. El servidor concederá un Token JWT que permitirá el uso de las diferentes funciones de la API.

Funciones: 

    POST:/templates/create
        Authorization: Bearer <JWT>
        Body: 
        {
            "nombre": "Bienvenida",
            "contenido": "<html>Hola ${nombre}...</html>" 
        }
    
    Se crea una nueva plantilla de correo vinculada al usuario. En "contenido" se envía en un string el HTML de la plantilla de correo con sus respectivos placeholders (variables)

    POST:/send
        Authorization: Bearer <JWT>
        Body: 
        {
            "destinatarios": ["juan@test.com", "Juan2@test.com"],
            "plantillaId": "BIENVENIDA_MFA",
            "datosVariables": {
                "nombre": "Juan",
                "codigoMFA": "123456"
            },
            "date?": "12/12/2025.10:00"
        }

    Se programa/envía un correo con plantilla seleccionada del usuario y las variables. 
