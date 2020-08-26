SAP Community Coding Challenge #3: Emoji manipulator
===

This is a submission to [SAP Community Coding Challenge #3][community_challenge].


How to run
---

Compile native Go library (or you can skip and stick with Java code only) with:

```shell
/go_native$ go build -buildmode=c-shared -o libgoemoji.so .
```

Copy ``libgoemoji.so`` library to ``java/native`` folder.

Then you can compile Java and start app with:

```shell
/java$ mvn compile
/java$ mvn exec:java
```

About this submission
---

There are two implementations provided and both validates input against [all known emojis listed by Unicode Consortium][emoji_definition].

### Java implementation ###

[Code point][code_point] manipulation to fulfill both requirements.

### Native with GoLang ###

I never needed to call a native code from Java when it was main my main programming language, so this was my personal challenge.
A friend pointed me [this post][go_jni] and I went with [jnr-ffi library][java_lib_jnr-ffi] (way simpler than JNI).

The correct approach to code would be using [runes][go_rune] (same thing as code points), but with strings is okay-ish and readable, almost literary doing the function name in code.

___

This repository is VS Code Remote Container ready
---

Just open each folder and it will prompt to create remote (Docker) container.


A hidden easter egg (challenge) is waiting for you!
---

A challenge within a challenge. I know, catching you off guard isn't funny, but solving this challenge is.

Tips:

- Triggered by AddJoiner using Go implementation.
- Combination of two different emojis that:
  - Were both introduced on Emoji 1.0.
  - Won't produce a valid emoji ZWJ when joined together.

You can check the code if you want, and even try to peek easter egg data, but it is encrypted and you'll need to solve this challenge and put the correct emojis (in correct sequence) to both activate easter egg / decrypt that data.

Brute force, mathematical analysis, random guessing or even reading this README again to find hidden clues, choose your way and solve it right away.

Good luck!

[community_challenge]: https://blogs.sap.com/2020/08/03/sap-community-coding-challenge-3/
[code_point]: https://en.wikipedia.org/wiki/Code_point
[go_jni]: https://blog.dogan.io/2015/08/15/java-jni-jnr-go/
[go_rune]: https://blog.golang.org/strings#TOC_5.
[java_lib_jnr-ffi]: https://github.com/jnr/jnr-ffi
[emoji_definition]: https://unicode.org/Public/emoji/13.0/
