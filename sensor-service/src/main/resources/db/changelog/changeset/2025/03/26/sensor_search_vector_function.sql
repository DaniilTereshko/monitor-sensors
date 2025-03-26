CREATE OR REPLACE FUNCTION sensor_search_vector_update() RETURNS TRIGGER AS $$
BEGIN
    NEW.search_vector := TO_TSVECTOR('english', COALESCE(NEW.name, '') || ' ' || COALESCE(NEW.model, ''));
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;