import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;



class Estorno {
    static Stream<Arguments> valoresEstorno() {
        return Stream.of(
            Arguments.of(100.0, 10.0, 110.0),
            Arguments.of(0.0,   5.0,   5.0),
            Arguments.of(50.0,  0.01, 50.01)
        );
    }

    @ParameterizedTest
    @MethodSource("valoresEstorno")
    void refundComCarteiraValida(double inicial, double valor, double saldoEsperado) {
        DigitalWallet wallet = new DigitalWallet("Ítalo", inicial);
        wallet.verify();

        assumeTrue(wallet.isVerified());
        assumeFalse(wallet.isLocked());

        wallet.refund(valor);

        assertEquals(saldoEsperado, wallet.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 0, -10.50})
    void deveLancarExcecaoParaRefundInvalido(double valor) {
        DigitalWallet wallet = new DigitalWallet("Ítalo", 700);
        wallet.verify();

        assumeTrue(wallet.isVerified());
        assumeFalse(wallet.isLocked());

        assertThrows(IllegalArgumentException.class, () -> wallet.refund(valor));
    }

    @Test
    void deveLancarSeNaoVerificadaOuBloqueada() {
        DigitalWallet wallet = new DigitalWallet("Ítalo", 250);

        assumeFalse(wallet.isVerified());
        assumeFalse(wallet.isLocked());

        assertThrows(IllegalStateException.class, () -> wallet.refund(100));
    }
    
}
