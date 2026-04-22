import threading
from singleton_lazy import LazyMongoManager
from singleton_sync import SyncMongoManager
from singleton_dcl import DCLMongoManager
from singleton_eager import eager_instance
from singleton_bill_pugh import BillPughMongoManager
from singleton_enum import EnumMongoManager

def thread_task(cls_or_instance, result_list):
    if hasattr(cls_or_instance, 'get_instance'):
        obj = cls_or_instance.get_instance()
    else:
        obj = cls_or_instance
    result_list.append(id(obj))

def test_concurrency(name, target_obj):
    print(f"\n{name} (Testing Thread Safety)...")
    results = []
    threads = [threading.Thread(target=thread_task, args=(target_obj, results)) for _ in range(5)]
    
    for t in threads: t.start()
    for t in threads: t.join()
    
    for rid in results:
        print(rid)
        
    unique_ids = set(results)
    if len(unique_ids) > 1:
        print(f"RESULT: {name} is NOT Thread Safe! ({len(unique_ids)} instances created)")
    else:
        print(f"RESULT: {name} is Thread Safe.")

if __name__ == "__main__":
    print("\n=== FULL THREAD SAFETY TEST ===")

    test_concurrency("LazySingleton", LazyMongoManager)
    test_concurrency("SyncSingleton", SyncMongoManager)
    test_concurrency("DCLSingleton", DCLMongoManager)
    test_concurrency("EagerSingleton", eager_instance)
    test_concurrency("BillPughSingleton", BillPughMongoManager)
    test_concurrency("EnumSingleton", EnumMongoManager.INSTANCE)