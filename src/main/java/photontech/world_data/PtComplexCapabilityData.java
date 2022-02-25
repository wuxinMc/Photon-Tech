package photontech.world_data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.storage.WorldSavedData;
import photontech.utils.capability.ISaveLoad;

import javax.annotation.Nonnull;
import java.util.*;

public abstract class PtComplexCapabilityData<T extends PtComplexCapabilityData.ISaveLoadWithID> extends WorldSavedData {
    public static final int INITIAL_SIZE = 128;

    protected Map<Integer, T> datas = new HashMap<>(INITIAL_SIZE);
    protected int nextID = 0;
    public static final String KEY = "Key";
    public static final String VALUE = "Value";
    public static final String DATAS = "Datas";
    public static final String NEXT_ID = "NextInt";

    public PtComplexCapabilityData(String name) {
        super(name);
    }

    public abstract void load(@Nonnull CompoundNBT nbt);

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT nbt) {
        CompoundNBT datasNBT = new CompoundNBT();
        ListNBT listNBT = new ListNBT();
        for (Map.Entry<Integer, T> e : datas.entrySet()) {
            CompoundNBT entryNBT = new CompoundNBT();
            entryNBT.putInt(KEY, e.getKey());
            entryNBT.put(VALUE, e.getValue().save(new CompoundNBT()));
            listNBT.add(entryNBT);
        }
        datasNBT.putInt(NEXT_ID, this.nextID);
        datasNBT.put(DATAS, listNBT);
        nbt.put(this.getId(), datasNBT);
        return nbt;
    }

    public T get(int id) {
        this.setDirty();
        return datas.get(id);
    }

    public void put(int key, @Nonnull T value) {
        datas.put(key, Objects.requireNonNull(value));
        this.setDirty();
    }

    public void remove(int id) {
        datas.remove(id);
        this.setDirty();
    }

    public int getSize() {
        return datas.size();
    }

    public interface ISaveLoadWithID extends ISaveLoad {

    }

    public int getNextID() {
        return nextID++;
    }

}

