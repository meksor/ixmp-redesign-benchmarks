import random
import typing
import pickle
import pytest

class TimeseriesEntryDTO(object):
    run_id: int
    model: str
    scenario: str
    version: int
    region: str
    variable: str
    unit: str
    meta: int
    subannual: str
    time: int
    year: int
    value: float
    
    def __init__(self):
        self.run_id = random.randint(0, 1000000)
        self.model = "x" * random.randint(10, 30)
        self.scenario = "x" * random.randint(10, 30)
        self.version = random.randint(0, 100)
        self.region = "x" * random.randint(10, 30)
        self.variable = "x" * random.randint(10, 30)
        self.unit = "x" * random.randint(10, 30)
        self.meta = random.randint(0, 1000000)
        self.subannual = "x" * random.randint(10, 30)
        self.time = random.randint(0, 1000000)
        self.year = random.randint(0, 3000)
        self.value = random.random()

def make_entry():
    return (TimeseriesEntryDTO(),) , {}

def test_pickle(benchmark):
    benchmark.pedantic(pickle.dumps, setup=make_entry, rounds=1_000_000)
