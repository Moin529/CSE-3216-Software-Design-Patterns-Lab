import time
from singleton_lazy import LazyMongoManager
from singleton_sync import SyncMongoManager
from singleton_dcl import DCLMongoManager
from singleton_eager import eager_instance
from singleton_bill_pugh import BillPughMongoManager
from singleton_enum import EnumMongoManager

def get_manager_instance(name, target):
    if name == "Eager": return target
    if name == "Enum": return target.INSTANCE
    return target.get_instance()

def run_performance_test():
    # Setup implementations list
    impls = [
        ("Lazy", LazyMongoManager),
        ("Sync", SyncMongoManager),
        ("DCL", DCLMongoManager),
        ("Eager", eager_instance),
        ("Bill Pugh", BillPughMongoManager),
        ("Enum", EnumMongoManager)
    ]

    results = []
    iterations = 100000

    for name, target in impls:
        # Measure Initialization (First Call)
        start_init = time.perf_counter()
        _ = get_manager_instance(name, target)
        end_init = time.perf_counter()
        init_time = end_init - start_init

        # Measure Access (Repeated Calls)
        start_access = time.perf_counter()
        for _ in range(iterations):
            _ = get_manager_instance(name, target)
        end_access = time.perf_counter()
        access_time = end_access - start_access
        
        results.append((name, init_time, access_time))

    # --- CLEAN TABULAR OUTPUT ---
    print("\n" + "="*60)
    print(f"{'Implementation':<15} | {'Init Time (s)':<15} | {'Access Time (s)':<15}")
    print("-" * 60)
    for name, itime, atime in results:
        print(f"{name:<15} | {itime:<15.8f} | {atime:<15.8f}")
    print("="*60)

    # --- COMPARISON ANALYSIS ---
    sync_time = results[1][2] # Access time for Sync
    dcl_time = results[2][2]  # Access time for DCL
    
    print("\n=== LIGHTWEIGHT VS SYNCHRONIZED COMPARISON ===")
    print(f"Synchronized Access Time: {sync_time:.6f}s")
    print(f"DCL (Lightweight) Access Time: {dcl_time:.6f}s")
    
    improvement = ((sync_time - dcl_time) / sync_time) * 100
    print(f"Optimization: Double-Checked Locking is {improvement:.2f}% faster than Synchronized.")

if __name__ == "__main__":
    run_performance_test()