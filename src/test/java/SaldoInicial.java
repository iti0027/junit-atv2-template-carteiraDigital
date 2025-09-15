import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.DigitalWallet;

class SaldoInicial {
     @Test
        void deveConfigurarSaldoInicialCorreto() {
            DigitalWallet wallet = new DigitalWallet("Ítalo", 700);
            assertTrue(wallet.getBalance() > 0);
        }

        @Test
        void deveLancarExcecaoParaSaldoInicialNegativo() {
            assertThrows(IllegalArgumentException.class, () -> {
                new DigitalWallet("Ítalo", -250);
            });
        }

    }