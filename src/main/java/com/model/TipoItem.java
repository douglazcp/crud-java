package com.model;

import javax.persistence.*;

@Entity
@Table(name = "tipo_item")
public class TipoItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_TIPO_ITEM")
	private long id;

	@Column(name = "DESC_TIPO_ITEM")
	private String descricao;

	@Column(name = "TIPO_ITEM")
	private String tipo;

	public TipoItem() {

	}

	public TipoItem(String descricao, String tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}

	public long getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getTipo(){
		return this.tipo;
	}

	public void setDescricao(String descricao){
		this.descricao = descricao;
	}

	public void setTipo(String tipo){
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Tutorial [id=" + id + ", descricao=" + descricao+", tipo="+ tipo+"]";
	}

}
