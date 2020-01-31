/*
 * 8 de nov de 2016
 */
package br.gov.ce.seinfra.gestel.model;

/**
 * Enums.
 * @author heber
 */
public final class Enums {

    public enum GENERO {
        MASCULINO, FEMININO
    }

    public enum TIPO_STATUS_REUNIAO {
        ABERTA, AGENDADA, ENVIO_DOCUMENTOS, ENCERRADA, CONCLUIDA, CANCELADA
    }

    public enum STATUS_DOCUMENTO {
        ABERTO, APROVADO, CANCELADO
    }

    public enum TIPO_DOCUMENTO {
        ATA, OFICIO, OUTROS
    }

    public enum CARGO_MEMBRO {
        SECRETARIO, PRESIDENTE
    }

    public enum TIPO_MEMBRO {
        TITULAR, SUPLENTE
    }

    public enum TIPO_ORGAO {
        AGÊNCIA, ASSEMBLÉIA, DEFENSORIA, EMPRESA, ENTIDADE_ESTADUAL, FUNDAÇÃO, INSTITUTO,
        MINISTERIO, POLICIA, PREFEITURA, PROCURADORIA, SECRETARIA, SUPERINTENDÊNCIA,
        UNIVERSIDADE, TRIBUNAL,
    }

    /** Privado. */
    private Enums() {}
}
