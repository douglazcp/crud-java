package com.repository;

import java.util.List;

import com.model.TipoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoItemRepository extends JpaRepository<TipoItem, Long> {
  //List<TipoItem> findByDescricaoTipo(String descricao, String tipo);
  List<TipoItem> findByDescricao(String descricao);
  List<TipoItem> findByTipo(String tipo);

  List<TipoItem> findByDescricaoAndTipo(String descricao, String tipo);
}
