USE STORE_DB;

DELIMITER //
-- Crear el procedimiento almacenado
CREATE PROCEDURE sp_filtrar_productos (
    IN p_nombre_categoria VARCHAR(100),
    IN p_genero VARCHAR(20),
    IN p_edad_sugerida VARCHAR(20)
)
BEGIN
    SELECT
        p.id_producto,
        p.nombre,
        p.descripcion,
        p.precio,
        p.imagen_url,
        p.talla,
        p.genero,
        p.edad_sugerida,
        p.id_categoria
    FROM
        producto p
    INNER JOIN
        categoria c ON p.id_categoria = c.id_categoria
    WHERE
        (p_nombre_categoria IS NULL OR c.nombre COLLATE utf8mb4_unicode_ci = p_nombre_categoria COLLATE utf8mb4_unicode_ci)
        AND (p_genero IS NULL OR p.genero COLLATE utf8mb4_unicode_ci = p_genero COLLATE utf8mb4_unicode_ci)
        AND (p_edad_sugerida IS NULL OR p.edad_sugerida COLLATE utf8mb4_unicode_ci = p_edad_sugerida COLLATE utf8mb4_unicode_ci);
END //
DELIMITER ;

CALL sp_filtrar_productos('Zapatos', NULL, '6-8 años');