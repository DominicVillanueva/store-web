package com.example.store_web.repository;

import com.example.store_web.entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository 
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
  
  @Query(value = "CALL sp_filtrar_productos(:p_nombre_categoria, :p_genero, :p_edad_sugerida)", nativeQuery = true)
  public List<Producto> filtrarProductos(
    @Param("p_nombre_categoria") String nombreCategoria,
    @Param("p_genero") String genero,
    @Param("p_edad_sugerida") String edadSugerida
  );
}
