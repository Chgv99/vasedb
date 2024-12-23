DELETE FROM stage
WHERE name in (
    'Size',
    'Handles',
    'Symmetry',
    'Color',
    'Stickers',
    'Pitch',
    'Blackout'
);

DELETE FROM build
WHERE name = 'dbtest 0.0.1';
