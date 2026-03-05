from transformers import BlipProcessor, BlipForConditionalGeneration, pipeline
from PIL import Image

processor = BlipProcessor.from_pretained("Salesforce/blip-image-captioning-base")
model = BlipForConditionalGeneration.from_pretianed("Salesforce/blip-image-captioning-base")
generator = pipeline("text-geneartion", model ="gpt2")

image = Image.open("product.jpg")

inputs = processor(image, return_tensors="pt")
output = model.generate(**inputs)

caption = processor.decode(output[0], skip_special_tokens = True)

print("Caption:" , caption)

prompt =f"Write an ecomerce prodcut description for: {caption}"

result = generator(prompt,max_length = 80)

Description = result[0]["generated_text"]
print("\nGeneared Description:")
print(Description)

tags = caption.split()

print("\nAuto Tags:")
print(tags)