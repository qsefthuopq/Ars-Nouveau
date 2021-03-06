package com.hollingsworth.arsnouveau.common.items;

import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;
import com.hollingsworth.arsnouveau.api.util.SpellRecipeUtil;
import com.hollingsworth.arsnouveau.common.lib.LibItemNames;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SpellParchment extends ModItem{
    public SpellParchment() {
        super(LibItemNames.SPELL_PARCHMENT);
    }

    @Override
    public void inventoryTick(ItemStack stack, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(!stack.hasTag())
            stack.setTag(new CompoundNBT());
    }

    public static void setSpell(ItemStack stack, String spellRecipe){
        stack.getTag().putString("spell", spellRecipe);
    }

    public static ArrayList<AbstractSpellPart> getSpellRecipe(ItemStack stack){
        if(!stack.hasTag())
            return null;
        return SpellRecipeUtil.getSpellsFromTagString(stack.getTag().getString("spell"));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag p_77624_4_) {
        if(!stack.hasTag() || stack.getTag().getString("spell").equals(""))
            return;

        StringBuilder tip = new StringBuilder();
        ArrayList<AbstractSpellPart> spellsFromTagString = SpellRecipeUtil.getSpellsFromTagString(stack.getTag().getString("spell"));
        for (int i = 0; i < spellsFromTagString.size(); i++) {
            AbstractSpellPart spellPart = spellsFromTagString.get(i);
            tip.append(spellPart.name);
            if(i < spellsFromTagString.size() - 1){
                tip.append(" -> ");
            }
        }
        tooltip.add(new StringTextComponent(tip.toString()));
    }
}
