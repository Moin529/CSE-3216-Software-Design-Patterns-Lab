from pymongo import MongoClient
import certifi

class MongoBase:
    def __init__(self, verbose=False):
        if verbose:
            print("Connecting to MongoDB Atlas...")
        
        self.uri = "mongodb+srv://mahmudurrahman2022415905_db_user:SAHjGbfrQLl2Ar3C@cluster0.syk2vo7.mongodb.net/?appName=Cluster0"
        
        try:
            self.client = MongoClient(self.uri, tlsCAFile=certifi.where())
            self.db = self.client['assignment_db']
            
            self.client.admin.command('ping')
        except Exception as e:
            print(f"Error connecting to Atlas: {e}")

    def get_database(self):
        return self.db

    def get_collection(self, name):
        return self.db[name]