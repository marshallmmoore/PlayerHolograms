package com.yahoo.marshallmoore55.playerholograms.Util;

import com.yahoo.marshallmoore55.playerholograms.HologramObject;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

public class ConversationPrompt extends StringPrompt {
    private ArmorStand armorstand;

    public ConversationPrompt(ArmorStand armorstand) {
        this.armorstand = armorstand;
    }
    @Override
    public String getPromptText(ConversationContext context) {
        return "Enter new display name";

    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        if (context.getForWhom() instanceof Player) {
            HologramObject.setMessage(input, armorstand);
            context.getForWhom().sendRawMessage("Changed display name to " + input);
        }
        return null;
    }
}
