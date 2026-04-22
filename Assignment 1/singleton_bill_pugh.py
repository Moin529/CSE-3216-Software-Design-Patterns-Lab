from mongo_base import MongoBase

class BillPughMongoManager(MongoBase):
    class _Holder:
        _instance = None 

    @classmethod
    def get_instance(cls):
        if cls._Holder._instance is None:
            cls._Holder._instance = cls()
        return cls._Holder._instance