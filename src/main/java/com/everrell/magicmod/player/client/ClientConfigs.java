package com.everrell.magicmod.player.client;

import com.everrell.magicmod.MaxsMagicMod;
import com.everrell.magicmod.gui.overlays.ManaBarOverlay;
import net.neoforged.neoforge.common.ModConfigSpec;


public class ClientConfigs {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec.ConfigValue<Boolean> SHOW_FIRST_PERSON_ARMS;
    public static final ModConfigSpec.ConfigValue<Boolean> SHOW_FIRST_PERSON_ITEMS;
    public static final ModConfigSpec.ConfigValue<Boolean> REPLACE_GHAST_FIREBALL;
    public static final ModConfigSpec.ConfigValue<Boolean> REPLACE_BLAZE_FIREBALL;
    public static final ModConfigSpec.ConfigValue<Integer> MANA_BAR_Y_OFFSET;
    public static final ModConfigSpec.ConfigValue<Integer> MANA_BAR_X_OFFSET;
    public static final ModConfigSpec.ConfigValue<Integer> MANA_TEXT_X_OFFSET;
    public static final ModConfigSpec.ConfigValue<Integer> MANA_TEXT_Y_OFFSET;
    public static final ModConfigSpec.ConfigValue<Boolean> MANA_BAR_TEXT_VISIBLE;
    public static final ModConfigSpec.ConfigValue<Boolean> ENABLE_BOSS_MUSIC;
    public static final ModConfigSpec.ConfigValue<ManaBarOverlay.Anchor> MANA_BAR_ANCHOR;
    public static final ModConfigSpec.ConfigValue<ManaBarOverlay.Display> MANA_BAR_DISPLAY;
    public static final ModConfigSpec.ConfigValue<ManaBarOverlay.Display> SPELL_BAR_DISPLAY; //reusing same enum
    public static final ModConfigSpec.ConfigValue<Integer> SPELL_BAR_Y_OFFSET;
    public static final ModConfigSpec.ConfigValue<Integer> SPELL_BAR_X_OFFSET;
    public static final ModConfigSpec.ConfigValue<Boolean> SHIELD_PARTICLE_COLLISIONS;
    public static final ModConfigSpec.ConfigValue<Boolean> SPELL_WHEEL_CONSISTENT_SIZE;
    public static final ModConfigSpec.ConfigValue<Double> SPELL_WHEEL_SCALE;
    public static final ModConfigSpec.ConfigValue<Boolean> SUMMONS_GLOW;
    public static final ModConfigSpec.ConfigValue<String> SUMMONS_GLOW_HEX_COLOR;
    public static final ModConfigSpec SPEC;

    static {
        BUILDER.comment("##############################################################################################");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##   ATTENTION: These are client configs. For gameplay settings, go to the SERVER CONFIGS   ##");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##                                                                                          ##");
        BUILDER.comment("##############################################################################################");
        BUILDER.comment("");

        BUILDER.push("UI");
        BUILDER.push("ManaBar");
        BUILDER.comment("By default (Contextual), the mana bar only appears when you are holding a magic item or are not at max mana.");
        MANA_BAR_DISPLAY = BUILDER.defineEnum("manaBarDisplay", ManaBarOverlay.Display.Contextual);
        BUILDER.comment("Used to adjust mana bar's position (11 is one full hunger bar up).");
        MANA_BAR_X_OFFSET = BUILDER.define("manaBarXOffset", 0);
        MANA_BAR_Y_OFFSET = BUILDER.define("manaBarYOffset", 0);
        MANA_BAR_TEXT_VISIBLE = BUILDER.define("manaBarTextVisible", true);
        MANA_BAR_ANCHOR = BUILDER.defineEnum("manaBarAnchor", ManaBarOverlay.Anchor.Hunger);
        MANA_TEXT_X_OFFSET = BUILDER.define("manaTextXOffset", 0);
        MANA_TEXT_Y_OFFSET = BUILDER.define("manaTextYOffset", 0);
        BUILDER.pop();
        BUILDER.push("SpellBar");
        BUILDER.comment("By default (Always), the spell bar always shows the spells in your equipped spellbook. Contextual will hide them when not in use.");
        SPELL_BAR_DISPLAY = BUILDER.defineEnum("spellBarDisplay", ManaBarOverlay.Display.Always);
        BUILDER.comment("Used to adjust spell bar's position.");
        SPELL_BAR_X_OFFSET = BUILDER.define("spellBarXOffset", 0);
        SPELL_BAR_Y_OFFSET = BUILDER.define("spellBarYOffset", 0);
        BUILDER.pop();
        BUILDER.push("RecastOverlay");

        BUILDER.pop();
        BUILDER.push("SpellWheel");
        SPELL_WHEEL_CONSISTENT_SIZE = BUILDER.comment("Whether to Spell Wheel size ignores the Gui scale option").define("ignoreGuiScale", false);
        SPELL_WHEEL_SCALE = BUILDER.comment("If ignoreGuiScale is enabled, apply this multiplier to its size").define("ignoreGuiScaleSizeMultiplier", 1.0);
        BUILDER.pop();
        BUILDER.pop();

        BUILDER.push("Animations");
        BUILDER.comment("What to render in first person while casting.");
        SHOW_FIRST_PERSON_ARMS = BUILDER.define("showFirstPersonArms", true);
        SHOW_FIRST_PERSON_ITEMS = BUILDER.define("showFirstPersonItems", true);
        BUILDER.pop();

        BUILDER.push("Renderers");
        BUILDER.comment("By default, both fireballs are replaced with an enhanced model used by fire spells.");
        REPLACE_GHAST_FIREBALL = BUILDER.define("replaceGhastFireballs", true);
        REPLACE_BLAZE_FIREBALL = BUILDER.define("replaceBlazeFireballs", true);
        BUILDER.pop();

        BUILDER.push("Music");
        ENABLE_BOSS_MUSIC = BUILDER.define("enableBossMusic", true);
        BUILDER.pop();

        BUILDER.push("Misc");
        SHIELD_PARTICLE_COLLISIONS = BUILDER.comment("Whether shield spells can collide with particles. Can affect performance. Default: true")
                .define("shieldParticleCollisions", true);
        BUILDER.pop();

        BUILDER.push("Summons");
        SUMMONS_GLOW = BUILDER.comment("Whether owned summons appear glowing to yourself. Default: true")
                .define("ownedSummonsGlow", true);
        SUMMONS_GLOW_HEX_COLOR = BUILDER.comment("Hex Color Value of owned summons glow outline. Default: 0xAAFFAA")
                .define("summonGlowColor", "0xAAFFAA");
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    public static int summonGlowColor;

    public static void onConfigReload() {
        try {
            summonGlowColor = Integer.decode(SUMMONS_GLOW_HEX_COLOR.get());
        } catch (Exception ignored) {
            MaxsMagicMod.LOGGER.warn("Failed to parse summonGlowColor \"{}\", reverting to default", SUMMONS_GLOW_HEX_COLOR.get());
            summonGlowColor = 0xAAFFAA;
        }
    }
}