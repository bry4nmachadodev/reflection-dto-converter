package br.com.bryan.refl;

import br.com.bryan.Endereco;
import br.com.bryan.Pessoa;
import br.com.bryan.PessoaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TransformatorTest {

    Pessoa pessoa = new Pessoa(1, "João", "1234");
    Endereco endereco = new Endereco(7, "Rua 10");


    @Test
    @DisplayName("DEVE transformar e verificar")
    public void shouldTranform() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Transformator transformator = new Transformator();
        PessoaDTO pessoaDTO = transformator.transform(pessoa);

        Assertions.assertInstanceOf(PessoaDTO.class, pessoaDTO);
        Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome());
        Assertions.assertEquals(pessoa.getCpf(), pessoaDTO.getCpf());
    }

    @Test
    @DisplayName("NÃO deve transformar ( LANÇA CLASS NOT FOUND EXCEPTION )")
    public void shouldNotTransform()    {
        Assertions.assertThrows(ClassNotFoundException.class, () -> {
            Transformator transformator = new Transformator();
            transformator.transform(endereco);
        });
    }

    @Test
    @DisplayName("Deve transformar quando algum campo for NULO")
    public void shouldTransformWhenSomeFieldIsNull() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Pessoa pessoaSemCPF = new Pessoa("João");
        Transformator transformator = new Transformator();
        PessoaDTO pessoaDTOSemCPF = transformator.transform(pessoaSemCPF);

        Assertions.assertEquals(pessoa.getNome(), pessoaDTOSemCPF.getNome());
        Assertions.assertNull(pessoaDTOSemCPF.getCpf());
    }
}
