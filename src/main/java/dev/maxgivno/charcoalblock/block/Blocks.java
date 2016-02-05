package dev.maxgivno.charcoalblock.block;

import dev.maxgivno.charcoalblock.helpers.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.registry.GameRegistry;


public enum Blocks {
    BLOCK_CHARCOAL("charcoalblock", new BlockCharcoalBlock(), CreativeTabs.tabBlock)
    ;

    private static boolean registered = false;
    public final Block block;
    private final String internalName;
    private final Class<? extends ItemBlock> itemBlockClass;
    private final CreativeTabs creativeTabs;

    Blocks(String internalName, Block block) {
        this(internalName, block, ItemBlock.class, null);
    }

    Blocks(String internalName, Block block, CreativeTabs creativeTabs) {
        this(internalName, block, ItemBlock.class, creativeTabs);
    }

    Blocks(String internalName, Block block, Class<? extends ItemBlock> itemBlockClass) {
        this(internalName, block, itemBlockClass, null);
    }

    Blocks(String internalName, Block block, Class<? extends ItemBlock> itemBlockClass, CreativeTabs creativeTabs) {
        this.internalName = internalName;
        this.block = block;
        this.itemBlockClass = itemBlockClass;
        this.creativeTabs = creativeTabs;
    }

    public static void registerAll() {
        if (registered)
            return;
        for (Blocks b : Blocks.values())
            b.register();
        registered = true;
    }

    public String getInternalName() {
        return internalName;
    }

    public String getStatName() {
        return StatCollector.translateToLocal(block.getUnlocalizedName().replace("tileentity.", "block."));
    }

    private void register() {
        GameRegistry.registerBlock(block.setCreativeTab(creativeTabs).setUnlocalizedName(internalName), itemBlockClass, internalName);
        LogHelper.info("Registered Block: " + internalName);
    }
}
