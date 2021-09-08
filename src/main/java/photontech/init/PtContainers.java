package photontech.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import photontech.block.heater.solid.PtHeaterContainer;
import photontech.block.crucible.PtCrucibleContainer;
import photontech.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@OnlyIn(Dist.CLIENT)
public class PtContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Utils.MOD_ID);
    public static final RegistryObject<ContainerType<PtCrucibleContainer>> CRUCIBLE_CONTAINER = CONTAINERS.register("crucible_container", () -> IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) -> new PtCrucibleContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().level)));
    public static final RegistryObject<ContainerType<PtHeaterContainer>> HEATER_CONTAINER = CONTAINERS.register("heater_container", () -> IForgeContainerType.create((int windowId, PlayerInventory inv, PacketBuffer data) -> new PtHeaterContainer(windowId, inv, data.readBlockPos(), Minecraft.getInstance().level)));
}