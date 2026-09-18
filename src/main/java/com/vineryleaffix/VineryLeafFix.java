package com.vineryleaffix;

import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This mod has no game logic of its own - it only ships the Mixin that patches
 * Vinery's PaleStemBlock. See PaleStemBlockMixin for the actual fix.
 */
@Mod(VineryLeafFix.MODID)
public class VineryLeafFix {

    public static final String MODID = "vineryleaffix";
    private static final Logger LOGGER = LoggerFactory.getLogger(VineryLeafFix.class);

    public VineryLeafFix() {
        LOGGER.info("VineryLeafFix loaded - Vinery grapevine leaf auto-growth is now disabled.");
    }
}
