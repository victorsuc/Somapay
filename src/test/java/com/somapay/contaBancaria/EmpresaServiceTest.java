package com.somapay.contaBancaria;

import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.somapay.contaBancaria.model.Empresa;
import com.somapay.contaBancaria.repository.EmpresaRepository;
import com.somapay.contaBancaria.service.EmpresaService;

@SpringBootTest
public class EmpresaServiceTest {

	@Autowired
	private EmpresaService empresaService;
	
	@MockBean
	private EmpresaRepository empresaRepository;
	
	@Test
	void deveRetornarTodasAsEmpresasCadastradas() {
        Empresa empresa1 = new Empresa(1l,"Empresa 1", "12345678912345");
        Empresa empresa2 = new Empresa(2l,"Empresa 2", "98765432198765");
        doReturn(Arrays.asList(empresa1, empresa2)).when(empresaRepository).findAll();

        List<Empresa> empresasEncontradas = empresaService.buscarTodasEmpresas();

        Assertions.assertEquals(2, empresasEncontradas.size());
	}
}
