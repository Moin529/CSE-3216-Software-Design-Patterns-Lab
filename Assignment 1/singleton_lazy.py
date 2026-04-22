from mongo_base import MongoBase

class LazyMongoManager(MongoBase):
    _instance = None

    @classmethod
    def get_instance(cls):
        if cls._instance is None:
            cls._instance = cls()
        return cls._instance