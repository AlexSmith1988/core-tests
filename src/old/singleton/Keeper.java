package old.singleton;

/**
 * Created by ilya on 26.12.2016.
 */
public class Keeper
{
  static volatile Data dataLock = null;
  static Data getDataByLock(){
    if (dataLock ==null)
      synchronized (Keeper.class)   {
        if (dataLock ==null)
          dataLock = new Data();
    }
    return dataLock;
  }

  static class DataHolder{
    public static final Data instance = new Data();
  }

  Data getDataBySH(){
    return DataHolder.instance;
  }
//  AtomicReference<Data> dataAtomic = new AtomicReference<>(new Data());


//  Data getDataByAR(){
//    dataAtomic.compareAndSet(null,new Data());
//    return dataAtomic.get();
//  }

}
