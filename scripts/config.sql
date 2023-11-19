--Crear base de datos
-- Correr solo la siguiente linea
CREATE DATABASE "proyecto-tic-lozano-dolivo-sotelo"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Cambiar conexión en pgAdmin a la base de datos creada

-- Correr programa server

-- Insertar superuser (correr las dos lineas)

INSERT INTO usuarios (id, nombre, apellido, correo, contrasena, pasaporte) 
              VALUES (1,'Super', 'User', 'superuser','superuser','superuser');
			  
INSERT INTO super_user (id) VALUES (1);