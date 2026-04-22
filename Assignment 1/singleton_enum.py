from enum import Enum
from pymongo import MongoClient
import certifi

class EnumMongoManager(Enum):
    INSTANCE = 1

    def __init__(self, value):
        print("Connecting to MongoDB Atlas (Enum)...")
        self.uri = "mongodb+srv://mahmudurrahman2022415905_db_user:SAHjGbfrQLl2Ar3C@cluster0.syk2vo7.mongodb.net/?appName=Cluster0"
        self.client = MongoClient(self.uri, tlsCAFile=certifi.where())
        self.db = self.client['assignment_db']

    def get_database(self):
        return self.db