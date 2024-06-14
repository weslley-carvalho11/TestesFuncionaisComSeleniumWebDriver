package br.ce.weslley.suites;

import br.ce.weslley.core.DriverFactory;
import br.ce.weslley.core.Propriedades;
import br.ce.weslley.test.TesteCadastro;
import br.ce.weslley.test.TesteRegrasCadastro;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteCadastro.class,
        TesteRegrasCadastro.class
})
public class SuiteTeste {

    @AfterClass
    public static void finalizaTudo() {
        DriverFactory.killDriver();
    }
}
