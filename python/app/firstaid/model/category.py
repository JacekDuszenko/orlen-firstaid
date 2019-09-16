class Category:
    def __init__(self, category_name, tags_en) -> None:
        self.category_name = category_name
        self.tags_en = tags_en

    def __repr__(self) -> str:
        return str(self.category_name) + ' ' + str(self.tags_en) + '\n'