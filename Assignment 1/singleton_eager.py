from mongo_base import MongoBase

class EagerMongoManager(MongoBase):
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(EagerMongoManager, cls).__new__(cls)
            cls._instance.__init__()
        return cls._instance

eager_instance = EagerMongoManager()