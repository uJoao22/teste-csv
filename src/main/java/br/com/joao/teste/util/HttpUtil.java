package br.com.joao.teste.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.joao.teste.dto.CepDTO;

public class HttpUtil {
	public static CepDTO fetchCEP(String cep) throws ClientProtocolException, IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(String.format("https://viacep.com.br/ws/%S/json/", cep));
		httpGet.addHeader("Content-Type", "application/json; charset=utf-8");
		
        HttpResponse response = httpClient.execute(httpGet);
		
		String json = HttpUtil.getResponseContentFromHttpResponse(response);
		
		return (CepDTO) deserializeJson(json, CepDTO.class);
	}
	
	public static String getResponseContentFromHttpResponse(HttpResponse httpResponse) throws UnsupportedOperationException, IOException {
		InputStream is = null;
		
		HttpEntity httpEntity = httpResponse.getEntity();
		
		String content = null;
		
		if(httpEntity != null) {
			is = httpEntity.getContent();
			content = IOUtils.toString(is, "utf-8");
			is.close();
		}	
		return content;
	}
	
	public static Object deserializeJson(String json, Class<?> clazz) throws IOException {
		return new ObjectMapper().readerFor(clazz).readValue(json);
	}
}
