import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.DigitalWallet;


class Deposito {
        
        @ParameterizedTest
        @ValueSource(doubles = {0.01, 1.5, 27, 700})
        void deveDepositarValoresValidos(double amount) {
            DigitalWallet digitalWallet = new DigitalWallet("Ítalo", 700);
            digitalWallet.deposit(amount);

            assertEquals(digitalWallet.getBalance(), amount, 27);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0, -50, -250})
        void deveLancarExcecaoParaDepositoInvalido(double amount) {
            DigitalWallet digitalWallet = new DigitalWallet("Ítalo", 0.0);
            assertThrows(IllegalArgumentException.class, () -> digitalWallet.deposit(amount));
        }
    }