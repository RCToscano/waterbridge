package br.com.waterbridge.enuns;

import java.util.ArrayList;
import java.util.List;

public enum AlarmePressaoEnum {
	
	PRESSAO_BAIXA_CRITICO(1, "Press�o est� baixa (N�vel Cr�tico)"),
	PRESSAO_BAIXA(2, "Press�o est� baixa"),
	PRESSAO_ALTA_CRITICO(3, "Press�o est� alta (N�vel Cr�tico)"),
	PRESSAO_ALTA(4, "Press�o est� alta");

	private int id;
	private String descricao;
	
	
	private AlarmePressaoEnum(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	
	
	public static List<AlarmePressaoEnum> listCodigos() {
		List<AlarmePressaoEnum> list = new ArrayList<>();
		for (AlarmePressaoEnum codigoEnum : values()) {
			list.add(codigoEnum);
		}
		return list;
	}



	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

}
