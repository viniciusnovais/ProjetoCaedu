package pdasolucoes.com.br.projetocaedu.mobile.Services;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

import pdasolucoes.com.br.projetocaedu.mobile.Model.Produto;

/**
 * Created by PDA on 28/09/2017.
 */

public class AuditoriaDePrecosProdutoService {

    private static String URL = "http://172.16.6.59:8081/papafila/wsproduto.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/ConsultaProdutoSortimento";

    private static String NAMESPACE = "http://tempuri.org/";

    public static List<Produto> getProdutoWS(String codigoFilial, String codigoBarra, String usuario) {

        SoapObject request = new SoapObject(NAMESPACE, "ConsultaProdutoSortimento");
        SoapObject response;
        List<Produto> lista = new ArrayList<>();

        try {

            PropertyInfo codFilialInfo = new PropertyInfo();
            codFilialInfo.setName("_filial");
            codFilialInfo.setValue(codigoFilial);
            codFilialInfo.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(codFilialInfo);

            PropertyInfo codBarraInfo = new PropertyInfo();
            codBarraInfo.setName("_codigoBarra");
            codBarraInfo.setValue(codigoBarra);
            codBarraInfo.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(codBarraInfo);

            PropertyInfo codUsuarioInfo = new PropertyInfo();
            codUsuarioInfo.setName("_usuario");
            codUsuarioInfo.setValue(usuario);
            codUsuarioInfo.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(codUsuarioInfo);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            response = (SoapObject) soapEnvelope.getResponse();

            SoapObject diffGramObject = (SoapObject) response.getProperty("diffgram");
            SoapObject documentElement = (SoapObject) diffGramObject.getProperty("DocumentElement");

            int soma = 0;
            for (int i = 0; i < documentElement.getPropertyCount(); i++) {
                SoapObject item = (SoapObject) documentElement.getProperty(i);
                Produto p = new Produto();
                p.setBarra(item.getPropertyAsString("barras"));
                p.setCodProduto(item.getPropertyAsString("codigo"));
                p.setTamanho(item.getPropertyAsString("tamanho"));
                p.setDescricao(item.getPropertyAsString("descricao"));
                p.setCor(item.getPropertyAsString("cor"));
                p.setVendas(Integer.parseInt(item.getPropertyAsString("VENDAS")));
                p.setPreco(Float.parseFloat(item.getPropertyAsString("PRECO")));
                p.setQtde(Integer.parseInt(item.getPropertyAsString("qde")));
                p.setRep(Integer.parseInt(item.getPropertyAsString("rep")));
                p.setIdade(Integer.parseInt(item.getPropertyAsString("IDADE")));
                p.setTipo_produto(item.getPropertyAsString("TIPO_PRODUTO"));
                p.setProp(item.getPropertyAsString("PROP"));
                p.setMargem(Float.parseFloat(item.getPropertyAsString("MARGEM_BRUTA")));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
