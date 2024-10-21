import org.example.Crypto
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestCrypto {

    @Test
    fun testCryptoWithKey30() {
        val crypto = Crypto(30U)
        val test1 = 0.toUByte()
        val test2 = 1.toUByte()
        val test3 = 5.toUByte()
        val test4 = 46.toUByte()
        val test5 = 255.toUByte()

        assertEquals(crypto.decrypt(crypto.crypt(test1)), test1)
        assertEquals(crypto.decrypt(crypto.crypt(test2)), test2)
        assertEquals(crypto.decrypt(crypto.crypt(test3)), test3)
        assertEquals(crypto.decrypt(crypto.crypt(test4)), test4)
        assertEquals(crypto.decrypt(crypto.crypt(test5)), test5)
    }

}