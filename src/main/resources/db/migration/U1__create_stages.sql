DELETE FROM stage
WHERE name in (
    'Size',
    'Handles',
    'Symmetry',
    'Color',
    'Stickers',
    'Pitch',
    'Blackout'
)
