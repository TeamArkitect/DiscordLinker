### [한국어가 필요하신가요?](https://github.com/TeamArkitect/DiscordLinker/readme/README.ko.md)

# DiscordLinker
An arkitect module that links mindustry server and discord.

## How to apply
* [Install Arkitect plugin](https://github.com/TeamArkitect/Arkitect/blob/main/readme/README.ko.md#%EC%84%A4%EC%B9%98)
* Download a JAR file from [Releases](https://github.com/TeamArkitect/DiscordLinker)
* `config/mods/Arkitect/modules` directory will be created on arkitect installed server.
* Move the downloaded JAR file to this directory.
* Start the server and exit.
* `config/mods/Arkitect/modules/DiscordLinker/settings.json` is created.
* Put your bot token in `token`, paste your copied channel id to link in `linked` array.
  (If you don't know how to get your bot token, see [this](https://github.com/TeamArkitect/DiscordLinker/README.md#create-discord-bot).)

## Create discord bot
* Go to [Discord Developer Portal](https://discord.dev). Login if you are not logined.
* Open `Applications`.
* Click `New Application` in the top right next to your profile.
* Enter your application name, agree to Discord ToS and click `Create`
* If you succeed, open `Bot`
* Click `Add bot`, and `Yes, do it!`
* Click `Reset Token` to generate bot token. **You should copy this token!**
* Enable `MESSAGE CONTENT INTENT` under `Privileged Gateway Intents`
* Now return to [here](https://github.com/TeamArkitect/DiscordLinker/README.md#how-to-apply) and finish setting up by pasting your token.
