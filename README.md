# Villager News — Fabric 26.3

This is a clean-room Java/Fabric recreation based on the behavior and content structure observed in the supplied Bedrock add-on.

## Included in this starter build
- Villager News automatic bulletins.
- Microphone item that interviews villagers.
- Handbook item with guidance.
- Mayor Hat, Moustache, Testificate Man Helmet, and Villager Nose placeholder items.
- Shapeless 3-paper Handbook recipe.
- Fabric server/client mod entrypoint.

## Important
The supplied Bedrock pack contains proprietary code, textures, models, animations, and sounds. This project does **not** copy those assets or source code. It implements original Java behavior inspired by the add-on.

## Build
1. Install Java 21.
2. Install a recent Gradle or use the Gradle wrapper you add for the exact Fabric Loom release.
3. Verify the Fabric Loader/Fabric API versions in `gradle.properties` for your exact Minecraft 26.3 build.
4. Run `gradle build`.
5. Put the resulting JAR from `build/libs/` into the Fabric `mods` folder.

Because Minecraft/Fabric 26.3 dependency coordinates can vary by release, the dependency versions are deliberately isolated in `gradle.properties` and may need to be updated to the exact 26.3 release you have installed.
