package com.somapay.contaBancaria;

import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.somapay.contaBancaria.exceptions.CnpjInvalidoException;
import com.somapay.contaBancaria.exceptions.RegistroJaExistenteException;
import com.somapay.contaBancaria.model.Empresa;
import com.somapay.contaBancaria.repository.EmpresaRepository;
import com.somapay.contaBancaria.service.EmpresaService;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class EmpresaServiceTest {

	@Autowired
	private EmpresaService empresaService;

	@MockBean
	private EmpresaRepository empresaRepository;

	@Test
	void deveRetornarTodasAsEmpresasCadastradas() {
		Empresa empresa1 = new Empresa(1l, "Empresa 1", "12345678912345");
		Empresa empresa2 = new Empresa(2l, "Empresa 2", "98765432198765");
		doReturn(Arrays.asList(empresa1, empresa2)).when(empresaRepository).findAll();

		List<Empresa> empresasEncontradas = empresaService.buscarTodasEmpresas();

		Assertions.assertEquals(2, empresasEncontradas.size());
	}

	@Test
	void deveInserirNovaEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setNome("Empresa 1");
		empresa.setCnpj("76387972000142");
		doReturn(empresa).when(empresaRepository).save(any());

		Empresa empresaInserida = empresaService.salvar(empresa);

		Assertions.assertNotNull(empresaInserida);
	}

	@Test
	void deveLancarExcecaoCnpjInvalidoAoInserirEmpresa() {
		Empresa empresa = new Empresa(1l, "Empresa 1", "12345678912345");

		Assertions.assertThrows(CnpjInvalidoException.class, () -> {
			empresaService.salvar(empresa);
		});
	}
	
	@Test
	void deveLancarExcecaoRegistroJaExistenteAoInserirEmpresa() {
		Empresa empresa1 = new Empresa(1l, "Empresa 1", "76387972000142");
		doReturn(true).when(empresaRepository).existsByCnpj(any());

		Assertions.assertThrows(RegistroJaExistenteException.class, () -> {
			empresaService.salvar(empresa1);
		});
	}
}
