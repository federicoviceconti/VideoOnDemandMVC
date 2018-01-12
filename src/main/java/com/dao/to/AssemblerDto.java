package com.dao.to;

import com.videoondemand.controller.Validate;

public class AssemblerDto {
	private String titolo = "", cast, descrizione, fileName;
	private int anno, idGenere, durata;

	private AssemblerDto(AssemblerBuilder assemblerBuilder){
		if(assemblerBuilder._titolo == null		
				|| Integer.parseInt(assemblerBuilder._anno) <= 0
				|| Integer.parseInt(assemblerBuilder._idGenere) <= 0) {
			throw new UnsupportedOperationException("DTO not correctly assembled!");
		}			

		this.titolo = assemblerBuilder._titolo;
		this.anno = Integer.parseInt(assemblerBuilder._anno);
		this.idGenere = Integer.parseInt(assemblerBuilder._idGenere);
		this.cast = Validate.checkIfIsNullOrEmpty(cast);
		this.fileName = Validate.checkIfIsNullOrEmpty(assemblerBuilder._fileName);
		this.descrizione = Validate.checkIfIsNullOrEmpty(descrizione);
	}
	
	public FilmGenereDto getDto() {
		return new FilmGenereDto(new FilmDto(titolo, idGenere, anno, fileName));
	}

	public static class AssemblerBuilder {
		private String _titolo, _anno, _idGenere, _fileName;

		public AssemblerBuilder setTitolo(String titolo) {
			this._titolo = titolo;
			return this;
		}

		public AssemblerBuilder setAnno(String anno) {
			this._anno = anno;
			return this;
		}

		public AssemblerBuilder setIdGenere(String idGenere) {
			this._idGenere = idGenere;
			return this;
		}
		
		public AssemblerBuilder setFileName(String fileName) {
			this._fileName = fileName;
			return this;
		}

		public AssemblerDto build() {
			return new AssemblerDto(this);
		}
	}
}