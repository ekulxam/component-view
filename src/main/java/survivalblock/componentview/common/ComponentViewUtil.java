package survivalblock.componentview.common;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.component.Component;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.text.WordUtils;
import survivalblock.componentview.common.config.ComponentViewConfig;

import java.util.*;

public class ComponentViewUtil {

    public static final String HIDE_TOOLTIP_KEY = "showintooltip";
    public static final List<Character> NEWLINE_STARTERS = new ArrayList<>();
    public static final List<Character> NEWLINE_ENDERS = new ArrayList<>();

    static {
        NEWLINE_STARTERS.add('{');
        NEWLINE_STARTERS.add('[');
        NEWLINE_STARTERS.add('(');

        NEWLINE_ENDERS.add('}');
        NEWLINE_ENDERS.add(']');
        NEWLINE_ENDERS.add(')');
    }

    /**
     * Runs the main point of the mod (appending components to tooltips)
     * @param lines the tooltip lines
     * @param component the component trying to be added to the tooltip
     */
    public static void componentView(List<Text> lines, Component<?> component){
        if (ComponentViewConfig.getRemovedComponents().contains(component.type().toString())) {
            return;
        }
        if (ComponentViewConfig.isShowNormallyHiddenComponents() && canBeHiddenFromTooltip(component) && !isHiddenFromTooltip(component)) {
            return;
        }
        boolean vibrant = (Screen.hasShiftDown() && !ComponentViewConfig.isShiftOppositeEffect()) || (!Screen.hasShiftDown() && ComponentViewConfig.isShiftOppositeEffect()); // XOR
        int glow = vibrant ? ComponentViewConfig.getComponentTypeColor().getRGB() : 5592405;
        MutableText mutableText;
        Identifier id = Registries.DATA_COMPONENT_TYPE.getId(component.type());
        if (ComponentViewConfig.isRemoveUnderscoresAndNamespace() && id != null) {
            String string = id.getPath();
            //
            string = string.replaceAll("_", " ");
            //noinspection deprecation
            string = WordUtils.capitalizeFully(string);
            mutableText = Text.literal(string);
        } else {
            boolean canTranslate = ComponentViewConfig.isTranslateThroughIdentifier() && (Screen.hasControlDown() || Screen.hasAltDown()) && id != null;
            mutableText = Text.literal(canTranslate ? id.toTranslationKey() : component.type().toString());
        }
        mutableText = mutableText.withColor(glow);
        appendValues(component, mutableText, vibrant, glow);
        lines.add(mutableText);
    }

    public static void appendValues(Component<?> component, MutableText mutableText, boolean vibrant, int glow) {
        if (ComponentViewConfig.isOnlyShowComponentTypes()) {
            return;
        }
        int lighter = vibrant ? ComponentViewConfig.getComponentValueColor().getRGB() : 5592405;
        mutableText.append(Text.literal(" : ").withColor(glow));
        String string = component.value().toString();
        if (ComponentViewConfig.isFormatUsingNewlines()) {
            string = whyDoINeedToIndentAgain(string);
        }
        mutableText.append(Text.literal(string).withColor(lighter));
    }

    /**
     * Uses strings to check if a component has a showInTooltip field and then captures that value.
     * @param component the component to check the showInTooltip field
     * @return true if the component is hidden from tooltips
     */
    public static boolean isHiddenFromTooltip(Component<?> component) {
        if (!canBeHiddenFromTooltip(component)) {
            return false;
        }
        String string = component.value().toString().toLowerCase();
        int index = string.indexOf(HIDE_TOOLTIP_KEY);
        int length = HIDE_TOOLTIP_KEY.length();
        string = string.substring(index + length + 1); // add 1 to remove the equals sign
        return string.startsWith(String.valueOf(false));
    }

    /**
     * Uses reflection to check if a component has a showInTooltip field
     * @param component the component to check the showInTooltip field
     * @return true if the component has that field
     */
    public static boolean canBeHiddenFromTooltip(Component<?> component) {
        return component.value().toString().toLowerCase().contains(HIDE_TOOLTIP_KEY);
    }

    /**
     * Uses a really hacky algorithm that took me four hours to write to properly indent lines
     * I'm done now
     * @param string the original string. This string is not mutable
     * @return the mutable string (the string I did all the operations on)
     */
    public static String whyDoINeedToIndentAgain(final String string) {
        // BIG O NOTATION: O(n) by itself (pretty good)
        // O(n^2) in total (I think) because of the ArrayList#contains operations
        int i = 0;
        int indentations = 0;
        //noinspection StringOperationCanBeSimplified
        String mutableString = string.toString(); // I'm not taking any chances
        while (i < mutableString.length()) {
            Character character = mutableString.charAt(i);
            boolean starter = NEWLINE_STARTERS.contains(character);
            boolean ender = NEWLINE_ENDERS.contains(character);
            boolean isAComma = (character == ',');
            StringBuilder blanks = new StringBuilder();
            if (starter || ender || isAComma) {
                if (starter) {
                    if (mutableString.length() > i + 1) {
                        if (!NEWLINE_ENDERS.contains(mutableString.charAt(i+1))) {
                            indentations++; // only increase indents for starters if the next char is not an ender
                        }
                    } else {
                        indentations++;
                    }
                } else if (ender) {
                    if (mutableString.length() > i + 1) {
                        if (mutableString.charAt(i + 1) != ',') {
                            indentations--; // only decrease indents for enders if the next char is not a comma
                        }
                    } else {
                        indentations--;
                    }
                } else {
                    if (NEWLINE_ENDERS.contains(mutableString.charAt(i - 1))) {
                        if (indentations > 1) { // just in case
                            indentations--; // decrease indents if the previous character was an ender
                        }
                        if (mutableString.length() > i + 1) {
                            StringBuilder stringBuilder = new StringBuilder(mutableString);
                            stringBuilder.deleteCharAt(i + 1);
                            mutableString = stringBuilder.toString();
                        }
                    }
                }
                blanks.append("   ".repeat(Math.max(0, indentations))); // append blanks
                if (starter) {
                    if (mutableString.length() > i + 1) {
                        char nextChar = mutableString.charAt(i + 1);
                        if (!(NEWLINE_ENDERS.contains(nextChar))) {
                            mutableString = insertBlanks(i, mutableString, blanks); // only insert blanks for starter if the next char is not an ender
                        }
                    } else {
                        mutableString = insertBlanks(i, mutableString, blanks);
                    }
                } else if (ender) {
                    if (mutableString.length() > i + 1) {
                        char nextChar = mutableString.charAt(i + 1);
                        if (nextChar != ',') {
                            StringBuilder stringBuilder = new StringBuilder(mutableString);
                            stringBuilder.deleteCharAt(i); // delete yourself, insert blanks, then append yourself at the end of those blanks, so you end up on the new line
                            mutableString = stringBuilder.toString();
                            mutableString = mutableString.substring(0, i) + "\n" + blanks + character + mutableString.substring(i);
                        }
                    } else {
                        StringBuilder stringBuilder = new StringBuilder(mutableString);
                        stringBuilder.deleteCharAt(i);
                        mutableString = stringBuilder.toString();
                        mutableString = mutableString.substring(0, i) + "\n" + blanks + character + mutableString.substring(i);
                    }
                } else {
                    mutableString = insertBlanks(i, mutableString, blanks);
                }
                if (starter) {
                    if (mutableString.length() > i + 1) {
                        if (!NEWLINE_ENDERS.contains(mutableString.charAt(i + 1))) {
                            i += 1 + blanks.length(); // only skip with blanks if next char is not an ender
                        }
                    } else {
                        i += 1 + blanks.length();
                    }
                } else if (ender) {
                    if (mutableString.length() > i + 1) {
                        char nextChar = mutableString.charAt(i + 1);
                        if (nextChar != ',') {
                            i += 1 + blanks.length(); // only skip with blanks if next char is not a comma (because we avoided the operations when the char was a comma)
                        }
                    } else {
                        i += 1 + blanks.length(); // "\n" counts as one character, and we add that to i, and then add the blanks length
                    }
                }
            }
            i++; // increment i
        }
        return mutableString; // return the string
    }

    /**
     * Inserts a new line and then blanks (indents) in a string at a given index
     * @param i index to insert
     * @param mutableString the string
     * @param blanks just spaces
     * @return the string after blanks have been inserted
     */
    private static String insertBlanks(int i, String mutableString, StringBuilder blanks) {
        return mutableString.substring(0, i + 1) + "\n" + blanks.toString() + mutableString.substring(i + 1);
    }

    public static boolean shouldShowInAdvancedTooltips() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return false;
        }
        GameOptions options = client.options;
        if (options == null) {
            return false;
        }
        return options.advancedItemTooltips && ComponentViewConfig.isAlwaysShowAdvancedTooltips();
    }
}
