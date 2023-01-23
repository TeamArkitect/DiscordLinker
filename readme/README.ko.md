# DiscordLinker
Mindustry 서버와 Discord를 연결하는 Arkitect 모듈입니다.

## 설치하기
* [Arkitect 플러그인을 설치하세요.](https://github.com/TeamArkitect/Arkitect/blob/main/readme/README.ko.md#%EC%84%A4%EC%B9%98)
* [Releases](https://github.com/TeamArkitect/DiscordLinker)에서 JAR 파일을 다운로드하세요.
* Arkitect를 설치한 서버에는 `config/mods/Arkitect/modules` 디렉터리가 자동으로 생성됩니다.
* 이 디렉터리에 다운로드한 JAR 파일을 넣으세요.
* 서버를 시작하고 완전히 켜진 후 다시 종료하세요.
* `config/mods/Arkitect/modules/DiscordLinker/settings.json`이 생성됩니다.
* 이 파일에서 `token`에 당신의 봇의 토큰을 넣고, `linked` 배열에 연결할 Discord 채널의 아이디를 복사해서 붙여넣으세요.
  (만약 봇 토큰을 얻는 법을 모르겠다면, [이것](https://github.com/TeamArkitect/DiscordLinker/readme/README.ko.md#%EB%94%94%EC%8A%A4%EC%BD%94%EB%93%9C-%EB%B4%87-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0)를 참고하세요.)

## 디스코드 봇 생성하기
* [Discord Developer Portal](https://discord.dev)로 접속하세요. 로그인 되어있지 않다면 로그인 하세요.
* `Applications` 탭을 여세요.
* 오른쪽 상단 프로필 옆의 `New Application` 버튼을 클릭하세요.
* 만들 Application의 이름을 입력하고 Discord ToS에 동의한 다음, `Create`를 클릭하세요.
* 성공했다면, `Bot` 탭을 여세요.
* `Add bot`을 클릭하고, `Yes, do it!`을 클릭하세요
* `Reset Token`을 눌러 봇의 토큰을 생성하세요. **이 토큰을 복사해두어야합니다!**
* `Privileged Gateway Intents` 카테고리의 `MESSAGE CONTENT INTENT`를 활성화하세요
* 이제 [여기](https://github.com/TeamArkitect/DiscordLinker/readme/README.ko.md#%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0)로 돌아가서 이 토큰을 넣고 설정을 완료하세요.
