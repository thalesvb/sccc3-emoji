/*
Since the native library loader I have used on Java have a different String signature from Go one,
I have to export as plain C pointers, so that loader can communicate with this code.

To generate native Go library, run:

go build -buildmode=c-shared -o libgoemoji.so .

After that, copy libgoemoji.so to native folder located in Java project.
*/
package main

import (
	"fmt"
	"strings"

	"github.com/thalesvb/sccc3/GoEmojiLib/easter"
)

import "C"

const zwj = "\u200D"

//AddJoiner handles native <-> Go conversion and calls
//private addJoiner implementation.
//export AddJoiner
func AddJoiner(emojis *C.char) *C.char {
	return C.CString(addJoiner(C.GoString(emojis)))
}

//RemoveJoiner handles native <-> Go conversion and calls
//private removeJoiner implementation.
//export RemoveJoiner
func RemoveJoiner(emoji *C.char) *C.char {
	return C.CString(removeJoiner(C.GoString(emoji)))
}

func addJoiner(emojis string) string {
	result, err := easter.Hatch(emojis)
	if err {
		result = strings.ReplaceAll(emojis, "", zwj)
		// It almost does the trick, but also adds it to leading and trailing positions.
		// We need just one more step to discard both.
		result = strings.Trim(result, zwj)
	}
	return result
}

func removeJoiner(emoji string) string {
	return strings.ReplaceAll(emoji, zwj, "")
}

func main() {
	// Just a safety check for exposed API.
	addEmojis := C.CString("👨👧")
	removeEmoji := C.CString("👨‍👩‍👧‍👦")
	fmt.Printf("AddJoiner (%s): %s\n", C.GoString(addEmojis), C.GoString(AddJoiner(addEmojis)))
	fmt.Printf("RemoveJoiner (%s): %s\n", C.GoString(removeEmoji), C.GoString(RemoveJoiner(removeEmoji)))
}
