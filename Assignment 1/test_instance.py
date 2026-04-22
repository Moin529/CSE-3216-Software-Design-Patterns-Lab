from singleton_lazy import LazyMongoManager
from singleton_sync import SyncMongoManager
from singleton_dcl import DCLMongoManager
from singleton_eager import eager_instance
from singleton_bill_pugh import BillPughMongoManager
from singleton_enum import EnumMongoManager

def run_test():
    print("\n=== INSTANCE VALIDATION TEST ===\n")

    # Lazy
    l1, l2 = LazyMongoManager.get_instance(), LazyMongoManager.get_instance()
    print(f"Lazy Singleton: {id(l1)} {id(l2)}")

    # Sync
    s1, s2 = SyncMongoManager.get_instance(), SyncMongoManager.get_instance()
    print(f"Sync Singleton: {id(s1)} {id(s2)}")

    # DCL
    d1, d2 = DCLMongoManager.get_instance(), DCLMongoManager.get_instance()
    print(f"DCL Singleton: {id(d1)} {id(d2)}")

    # Eager
    e1, e2 = eager_instance, eager_instance
    print(f"Eager Singleton: {id(e1)} {id(e2)}")

    # Bill Pugh
    b1, b2 = BillPughMongoManager.get_instance(), BillPughMongoManager.get_instance()
    print(f"Bill Pugh Singleton: {id(b1)} {id(b2)}")

    # Enum
    n1, n2 = EnumMongoManager.INSTANCE, EnumMongoManager.INSTANCE
    print(f"Enum Singleton: {id(n1)} {id(n2)}")

if __name__ == "__main__":
    run_test()