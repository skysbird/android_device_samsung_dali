$(call inherit-product, device/samsung/e120l/full_e120l.mk)

# Inherit some common CM stuff.
$(call inherit-product, vendor/mk/config/gsm.mk)

# Enhanced NFC
$(call inherit-product, vendor/mk/config/nfc_enhanced.mk)

# Inherit some common CM stuff.
$(call inherit-product, vendor/mk/config/common_full_phone.mk)

PRODUCT_BUILD_PROP_OVERRIDES += PRODUCT_NAME=SHV-E120L TARGET_DEVICE=SHV-E120L BUILD_FINGERPRINT="samsung/SHV-E120L/SHV-E120L:4.1.2/JZO54K/E120LKLJMA4:user/release-keys" PRIVATE_BUILD_DESC="SHV-E120L-user 4.1.2 JZO54K E120LKLJMA4 release-keys"

# Boot animation
TARGET_SCREEN_HEIGHT := 1280
TARGET_SCREEN_WIDTH := 720

PRODUCT_NAME := mk_e120l
PRODUCT_DEVICE := e120l