package photontech.utils.tileentity;

import photontech.utils.capability.fluid.PtMultiFluidTank;

public interface IMultiTankTile {

    PtMultiFluidTank getFluidTanks();

    default PtMultiFluidTank createFluidTanks(int tanks, int capacity) {
        return new PtMultiFluidTank(tanks, capacity);
    }
}
