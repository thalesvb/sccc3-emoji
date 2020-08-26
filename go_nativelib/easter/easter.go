/*
Package easter hides an easter egg for you.
It ain't much, but it's honest work.
*/
package easter

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"fmt"
	"hash/crc64"
)

var crcTable = crc64.MakeTable(crc64.ISO)
var easterDataStorage = []byte{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 53, 3, 32, 117, 130, 80, 90, 180, 128, 117, 107, 70, 208, 145, 41, 158, 195, 243, 31, 188, 218, 99, 172, 181, 43, 17, 8, 15, 108, 126, 70, 61, 23, 11, 52, 183, 17, 64, 215, 189, 148, 133, 143, 87, 253, 77, 122, 54, 249, 93, 55, 147, 27, 67, 16, 255, 237, 88, 67, 92, 227, 164, 242}

const easterKeyCRC = 17786551427874613110

// Hatch your surprise with the correct pair of emojis.
func Hatch(emojis string) (string, bool) {
	return hatchEasterEgg(emojis)
}

func hatchEasterEgg(emojis string) (string, bool) {
	if len(emojis) == 8 {
		masterKey, unlockKey := transformIntoKey(emojis)
		keyCRC := crc64.Checksum([]byte(fmt.Sprint(masterKey)), crcTable)
		if unlockKey == 10 && keyCRC == easterKeyCRC {

			message := fmt.Sprintf(`Whoa! An easter egg!! %s
Here is your prize: %s
Enjoy it!`, emojis, decryptEaster(masterKey))
			return message, false
		}
	}
	return "", true
}

/*
createEasterData encrypts payload with a masterKey.
It was called before hand to have that data stored on library at compile time.
Returns encrypted payload and Master Key CRC.
Print it with fmt.Printf("%d\n") and then store it as a Go byte array.
*/
func createEasterData(masterKey int, payload string) ([]byte, uint64) {
	gcm := createEasterGCM(masterKey)
	nonce := make([]byte, gcm.NonceSize())
	sealedData := gcm.Seal(nonce, nonce, []byte(payload), nil)
	keyCRC := crc64.Checksum([]byte(fmt.Sprint(masterKey)), crcTable)
	return sealedData, keyCRC
	//
}

/*
Emojis are the key to unlock the easter, you have to discover which ones does it.
*/
func transformIntoKey(emojis string) (int, int) {
	masterKey := 1
	unlockKey := 0
	for index, runeValue := range emojis {
		runeInteger := int(runeValue)
		masterKey *= runeInteger
		unlockKey += runeInteger / ((2 - index) * 5)
	}

	return masterKey, unlockKey
}
func decryptEaster(key int) string {
	gcm := createEasterGCM(key)
	nonceSize := gcm.NonceSize()
	nonce, result := easterDataStorage[:nonceSize], easterDataStorage[nonceSize:]
	dehashed, _ := gcm.Open(nil, nonce, result, nil)
	return string(dehashed)
}

// Encryption cipher must be 32 bytes lengh.
// I won't tell you the length of original cipher and just repeat it
// until fulfills that length.
func createEasterGCM(masterKey int) cipher.AEAD {
	var buffer bytes.Buffer
	textKey := fmt.Sprint(masterKey)
	for buffer.Len() < 32 {
		buffer.WriteString(textKey)
	}
	cKey := make([]byte, 32)
	buffer.Read(cKey)
	c, _ := aes.NewCipher(cKey)
	gcm, _ := cipher.NewGCM(c)
	return gcm
}
