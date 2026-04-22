import threading
from mongo_base import MongoBase

class DCLMongoManager(MongoBase):
    _instance = None
    _lock = threading.Lock()

    @classmethod
    def get_instance(cls):
        if not cls._instance:
            with cls._lock:
                if not cls._instance:
                    cls._instance = cls()
        return cls._instance